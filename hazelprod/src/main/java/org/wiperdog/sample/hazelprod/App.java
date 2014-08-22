package org.wiperdog.sample.hazelprod;

import java.util.concurrent.BlockingQueue;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        BlockingQueue<String> queue = h.getQueue("my-distributed-queue");
        
        System.err.println("Starting Hazelcast demo: producer");

        for (int i = 0;i < 100;++i) {
        	queue.add("test(" + i + ")");
        }
    }
}
