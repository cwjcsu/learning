/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年10月13日  Created
 */
package com.cwjcsu.learning.concurrent.concurrent_samples.pubsub;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author atlas
 *
 */
public class PubSub {

	private LinkedList<Object> datas = new LinkedList<Object>();

	/**
	 * 生成一批数据，放入到队列，并通知消费者
	 */
	public void produce(List<Object> objs) {
		synchronized (datas) {
			datas.addAll(objs);
			datas.notifyAll();
		}
	}

	/**
	 * 消费一项数据，如果队列为空则等待生产者
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public Object consume() throws InterruptedException {
		synchronized (datas) {
			if (datas.size() == 0) {
				datas.wait();
			}
			return datas.removeFirst();
		}
	}

	private Random r = new Random();

	class ProducerThread extends Thread {
		@Override
		public void run() {
			while (true) {
				// 随机产生一批数据，个数为[1,10]
				List<Object> newDatas = new ArrayList<Object>();
				int batchCount = 1 + r.nextInt(10);
				for (int i = 0; i < batchCount; i++) {
					newDatas.add("D" + r.nextInt(Integer.MAX_VALUE));
				}
				// 放入队列
				System.out.println("生产者生成数据:" + newDatas);
				PubSub.this.produce(newDatas);
				try {
					// 随机休眠1~3s
					Thread.sleep(1000 + r.nextInt(2000));
				} catch (InterruptedException e) {
				}
			}
		}
	}

	class ConsumerThread extends Thread {
		private String name;

		public ConsumerThread(String name) {
			super();
			this.name = name;
		}

		@Override
		public void run() {
			while (true) {
				try {
					// 消费数据
					Object data = consume();
					System.out.println("消费者 " + name + "消费了：" + data);
					// 随机休眠1~3s
					Thread.sleep(1000 + r.nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		PubSub pubsub = new PubSub();
		ProducerThread producer = pubsub.new ProducerThread();
		producer.start();
		ConsumerThread consumerB = pubsub.new ConsumerThread("B");
		ConsumerThread consumerC = pubsub.new ConsumerThread("C");
		ConsumerThread consumerD = pubsub.new ConsumerThread("D");
		consumerB.start();
		consumerC.start();
		consumerD.start();
		try {
			producer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
