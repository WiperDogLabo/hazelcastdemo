package org.wiperdog.sample.hazelconsum2;

import java.util.concurrent.BlockingQueue;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;


class ConsumerThread<T> extends Thread {
	final BlockingQueue<T> queue;
	
	public ConsumerThread(BlockingQueue<T> q) {
		this.queue = q;
	}
	
	public void run() {
		
        T item = null;
        
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

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	int qidstart = 0;
    	int qidend = 0;
    	if (args.length > 0) {
    		qidstart = Integer.parseInt(args[0]);
    		qidend = qidstart;
    	}
    	if (args.length > 1) {
    		qidend = Integer.parseInt(args[1]);
    	}
    	Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        BlockingQueue<String> queues[] = new BlockingQueue[qidend - qidstart + 1];
        
        System.err.println("Starting Hazelcast demo: receiver, " + qidstart + " ~ " + qidend);
        for (int i = 0;i < qidend - qidstart + 1;++i) {
        	queues[i] = h.getQueue("my-distributed-queue" + (qidstart + i));
        	(new ConsumerThread<String>(queues[i])).start();
        }
        
    }
}
