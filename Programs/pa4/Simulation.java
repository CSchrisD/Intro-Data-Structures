//Christina Duran
//1323418
//cmps012b/m
//8/3/14
//simulation of queues in line waiting to be processed at certain times
//Simulation.java

import java.io.*;
import java.util.Scanner;

class Simulation{
  public static void main(String[] args) throws IOException {
    Scanner in= new Scanner(new File(args[0]));
    PrintWriter report = new PrintWriter(new FileWriter(args[0]+".rpt"));
    PrintWriter reportTRC = new PrintWriter(new FileWriter(args[0]+".trc"));
    int numJobs=0;
    Queue storage = new Queue();
    Queue finishedJobs = new Queue();
    Job token;
    int lineNum=1;

    while(in.hasNextLine()){
      String [] s = in.nextLine().split(" ");
      if(lineNum>1){
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[s.length-1]);
        token= new Job(a,d);
        storage.enqueue(token);
      }
      numJobs++;
      lineNum++;
    }

    numJobs = numJobs-1;
    int time = 0;

    report.println("Report file :");
    report.println( numJobs + " Jobs:");
    report.println(storage);
    report.println();
    report.println("************************");

    reportTRC.println("Trace file:");
    reportTRC.println( numJobs + " Jobs:");
    reportTRC.println(storage);
    reportTRC.println();

    for(int n=1; n<numJobs; n++){

      int storageNum = storage.length();
      Queue[] processor = new Queue[n];
      if(n<=1){
        reportTRC.println("************************");
        reportTRC.println( n+ " processor:");
        reportTRC.println("************************");
      }else{
        reportTRC.println("************************");
        reportTRC.println( n+ " processors:");
        reportTRC.println("************************");
      }
      reportTRC.println( "time= " +time);

      for(int k=0;k<n;k++){
        processor[k] = new Queue();
      }

      reportTRC.println("0: "+ storage);

      for(int k=1;k<n+1;k++){
        reportTRC.println(k+": "+processor[k-1]);
      }

      reportTRC.println();

      while(finishedJobs.length() < storageNum){

        Job currentJob = (Job) storage.peek();
        int minTime = currentJob.getArrival();
        storage.dequeue();
        int finTime=0;

        if(currentJob.getArrival() == minTime){
          int minIndex=0;
          int min = processor[0].length();
          int procNum = minIndex;

          for(int j=0; j<n; j++){
            if(processor[j].length() < min){
              minIndex=j;
            }
          }

          processor[minIndex].enqueue(currentJob);

          for(int k=0; k<n; k++){
            if( !processor[k].isEmpty() ){
              if( ((Job) processor[k].peek()).getFinish() == -1){
                ( (Job) processor[k].peek()).computeFinishTime(minTime);
              }
            }
          }

          reportTRC.println("time= " + minTime);
          reportTRC.println("0: " + storage);

          for(int j=1; j<n+1; j++){
            reportTRC.println(j + ":" + processor[j-1]);
          }
          reportTRC.println();
        }
        storage.enqueue(currentJob);

        Job finJob = (Job) processor[0].peek();
        int finIndex = 0;
        int finProcNum = finIndex+1;

        for(int j=0; j<n; j++){
          if(!processor[j].isEmpty()){
            Job job = (Job) processor[j].peek();
            finJob = job;
            if(job.getFinish() == minTime){
              finIndex = j;
            }
          }
        }

        for(int j= 0; j<n; j++){
          if( !processor[j].isEmpty()){
            if(((Job)processor[j].peek()).getFinish() > minTime){
              finTime =((Job) processor[j].peek()).getFinish();
            }
          }
        }

        if(currentJob.getFinish() == finTime){
          finishedJobs.enqueue(finJob);
          processor[finIndex].dequeue();

          reportTRC.println("time= " + finTime);
          reportTRC.println("0: " + storage);

          for(int j=1; j<n+1; j++){
            reportTRC.println(j + ":" + processor[finIndex]);
          }
          reportTRC.println();
        }
      }

      int totalWait=0;
      int maxWait=0;
      double avgWait=0;
      int len = finishedJobs.length();

      for(int w = 0; w<len; w++){
        Job calcJob = (Job) finishedJobs.peek();
        totalWait = totalWait + calcJob.getDuration();
        if(maxWait < calcJob.getDuration()){
          maxWait = calcJob.getDuration();
        }
        finishedJobs.dequeue();
      }
      avgWait = (double) totalWait/n;

      if(n==1){
        report.println(n + " processor: totalWait="+ totalWait + " ,maxWait=" + maxWait + " ,averageWait=" + avgWait);
      }else{
        report.println(n + " processors: totalWait="+ totalWait + " ,maxWait=" + maxWait + " ,averageWait=" + avgWait);
      }

      for(int g = 0; g<=storageNum-1; g++){
        Job refreshJob = (Job) storage.peek();
        storage.dequeue();
        refreshJob.resetFinishTime();
        storage.enqueue(refreshJob);
      }
    }

    in.close();
    reportTRC.close();
    report.close();
  }
}