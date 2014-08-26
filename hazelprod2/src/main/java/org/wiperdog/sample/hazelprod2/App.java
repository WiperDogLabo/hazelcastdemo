package org.wiperdog.sample.hazelprod2;

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
    	int numq = 10;
    	Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        BlockingQueue<String> [] queues = new BlockingQueue[numq];
        for (int i = 0;i < numq;++i) {
            queues[i] = h.getQueue("my-distributed-queue" + i);
        }
        
        System.err.println("Starting Hazelcast demo: producer");

        for (int j = 0;j < 100;++j) {
            for (int i = 0;i < numq;++i) {
	        	queues[i].add("test(" + i + ")(" + j + ")");
	        }
        }
    }
}
