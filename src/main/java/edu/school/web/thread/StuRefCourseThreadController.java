package edu.school.web.thread;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.school.dao.StudentDao;
import edu.school.service.StuRefCourseService;
import edu.school.utils.HttpRequest;

@Controller
@RequestMapping({"/threads"})
public class StuRefCourseThreadController {

	@Autowired
	StuRefCourseService stuRefCourseService;
	
	@Autowired
	StudentDao studentDao;
	
	@RequestMapping(value="thread", method=RequestMethod.GET)
	public @ResponseBody void getStudentById(@RequestParam(value="num") int num) {

		final ConcurrentLinkedQueue<HashMap> bq = new ConcurrentLinkedQueue<HashMap>();
	    Runnable producerRunnable = new Runnable()
	    {
	        int i = 1;
	        int j = 1;
	        public void run()
	        {
	            while (true)
	            {
	                try
	                {
	                    System.out.println("我生产了一个" + i++ + " and " + j++);
	                    HashMap hm = new HashMap();
	                    hm.put(i, j);
	                    bq.add(hm);
	                    System.out.println("QUEUE_1=" + bq.size());
	                    Thread.sleep(1000);
	                } 
	                catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    };
	    Runnable customerRunnable = new Runnable()
	    {
	        public void run()
	        {
	            while (true)
	            {
	                try
	                {
	                    System.out.println("我消费了一个" + bq.poll());
	                    
	                    System.out.println("QUEUE_2=" + bq.size());
	                    Thread.sleep(3000);
	                } 
	                catch (InterruptedException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    };
	    Thread producerThread = new Thread(producerRunnable);
	    Thread customerThread = new Thread(customerRunnable);
	    producerThread.start();
	    customerThread.start();
		
		
	}
	@RequestMapping(value="cyclicBarrier", method=RequestMethod.GET)
	public @ResponseBody void startAtSameTime(@RequestParam(value="num") int num) {
		System.out.println("in ?");
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		final CyclicBarrier barrier = new CyclicBarrier(num);
		
		for(int k = 0; k < num; k++){
			
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Worker's waiting");
					try {
						//线程在这里等待，直到所有线程都到达barrier。
						barrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					
					Random r = new Random();
					int stu_id = r.nextInt(3) + 1;
					int course_id = r.nextInt(3) + 1;
					String parm = "stu_id=" + stu_id + "&course_id=" + course_id;
					String str = "";
					str = HttpRequest.sendGet("http://localhost:8080/grabclass/refRest", parm);
					System.out.println("HTTP返回结果：" + str);
				}
			});
			
			thread.start();
			/*try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		
		System.out.println("结束");
	}
	
	public static void main(String args[]){
		System.out.print("输入");
		Scanner scan = new Scanner(System.in);
		String read = scan.nextLine();
		System.out.println("输入数据："+read); 
		int num = Integer.parseInt(read);
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		final CyclicBarrier barrier = new CyclicBarrier(num);
		
		for(int k = 0; k < num; k++){
			
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Worker's waiting");
					try {
						//线程在这里等待，直到所有线程都到达barrier。
						barrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					
					Random r = new Random();
					int stu_id = r.nextInt(3) + 1;
					int course_id = r.nextInt(3) + 1;
					String parm = "stu_id=" + stu_id + "&course_id=" + course_id;
					String str = "";
					str = HttpRequest.sendGet("http://localhost:8080/grabclass/refRest", parm);
					System.out.println("HTTP返回结果：" + str);
				}
			});
			
			thread.start();
			/*try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		
		System.out.println("结束");
	}
	
}
