package org.wiperdog.sample.hazeltest;

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
        
        System.err.println("Starting Hazelcast demo: receiver");
        
        String item = null;
        try {
			while ((item = queue.take()) != null) {
				System.out.println("item=" + item);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
