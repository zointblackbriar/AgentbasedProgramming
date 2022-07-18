package de.tudresden.agentontology.lightjasonintegration;

/*
 * @cond LICENSE
 * ######################################################################################
 * # LGPL License                                                                       #
 * #                                                                                    #
 * # This file is part of the LightJason                                                #
 * # Copyright (c) 2015-16, LightJason (info@lightjason.org)                            #
 * # This program is free software: you can redistribute it and/or modify               #
 * # it under the terms of the GNU Lesser General Public License as                     #
 * # published by the Free Software Foundation, either version 3 of the                 #
 * # License, or (at your option) any later version.                                    #
 * #                                                                                    #
 * # This program is distributed in the hope that it will be useful,                    #
 * # but WITHOUT ANY WARRANTY; without even the implied warranty of                     #
 * # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                      #
 * # GNU Lesser General Public License for more details.                                #
 * #                                                                                    #
 * # You should have received a copy of the GNU Lesser General Public License           #
 * # along with this program. If not, see http://www.gnu.org/licenses/                  #
 * ######################################################################################
 * @endcond
 */


import org.lightjason.agentspeak.language.CLiteral;
import org.lightjason.agentspeak.language.CRawTerm;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.CTrigger;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.ITrigger;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * main application with runtime
 */
public class AgentApp
{

    static
    {
        // disable logger
        LogManager.getLogManager().reset();
    }

    /**
     * private constructor to avoid any instantiation
     */
    private AgentApp()
    {
    }


    /**
     * main method
     *
     * @param args command-line arguments
     */
    public static void main(String[] args )
    {
        if ( args.length < 2 )
            throw new RuntimeException( "arguments are not set: ASL script, number of agents" );

        // parameter of the command-line arguments:
        // 1. ASL file
        // 2. number of agents
        // 3. number of iterations (if not set maximum integer)
        final Set<MyAgent> l_agents;
        try
            (
                final FileInputStream l_stream = new FileInputStream( args[0] );
            )
        {
            // agent factory for generating an unmodifyable set
            l_agents = Collections.unmodifiableSet(
                new MyAgentGenerator( l_stream )
                    .generatemultiple( Integer.parseInt( args[1] ) )
                    .collect( Collectors.toSet() )
            );
        }
        catch ( final Exception l_exception )
        {
            l_exception.printStackTrace();
            return;
        }

        // runtime call (with parallel execution)
        IntStream
            .range(
                0,
                args.length < 3
                ? Integer.MAX_VALUE
                : Integer.parseInt( args[2] )
            )
            .forEach( j -> l_agents.parallelStream()
                                   .forEach( i ->
                                   {
                                       try
                                       {
                                           i.call();

                                           // call the add-goal trigger with 50% chance
                                           // and put a random value [0, 100] into the literal
                                           if ( Math.random() < 0.5 )
                                               i.trigger(
                                                   CTrigger.from(

                                                       // trigger-type
                                                       ITrigger.EType.ADDGOAL,

                                                       // goal-literal with value
                                                       CLiteral.from(
                                                           "special-goal",
                                                           CRawTerm.from( 100 * Math.random() )
                                                       )
                                                   )
                                               );
                                       }
                                       catch ( final Exception l_exception )
                                       {
                                           l_exception.printStackTrace();
                                       }
                                   } ) );
    }
}
