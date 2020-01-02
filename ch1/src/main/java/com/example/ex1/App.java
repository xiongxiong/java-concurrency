package com.example.ex1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.printf("Minimum Priority: %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Normal Priority: %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Maximum Priority: %s\n", Thread.MAX_PRIORITY);
    
        int threadCount = 20;
        Thread threads[];
        Thread.State status[];
        threads = new Thread[threadCount];
        status = new Thread.State[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Calculator());
            if ((i % 2) == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("My Thread " + i);
        }

        try (
            FileWriter file = new FileWriter("./log.txt");
            PrintWriter pw = new PrintWriter(file)
        ) {
            for (int i = 0; i < threadCount; i++) {
                pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }
            for (int i = 0; i < threadCount; i++) {
                threads[i].start();
            }
            
            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < threadCount; i++) {
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(pw, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }

                finish = true;
                for (int i = 0; i < threadCount; i++) {
                    finish = finish && (threads[i].getState() == State.TERMINATED);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
        pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Main : Priority: %d\n", thread.getPriority());
        pw.printf("Main : Old State: %s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : *****************************************\n");
    }
}

// <properties>
// <java.version>1.8</java.version>
// <maven.compiler.source>1.8</maven.compiler.source>
// <maven.compiler.target>1.8</maven.compiler.target>
// <exec.mainClass>com.example.App</exec.mainClass>
// <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
// </properties>
// <build>
//     <plugins>
//       <plugin>
//         <groupId>org.apache.maven.plugins</groupId>
//         <artifactId>maven-jar-plugin</artifactId>
//         <version>2.4</version>
//         <configuration>
//           <archive>
//             <manifest>
//               <mainClass>com.example.App</mainClass>
//             </manifest>
//           </archive>
//         </configuration>
//       </plugin>
//     </plugins>
//   </build>

// mvn clean compile exec:java