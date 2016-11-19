import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.*;

class WorkersFrame extends JFrame {
	ThreadedClient client;
    public WorkersFrame(Socket sock) {
    	client = new ThreadedClient(sock);
                int num=50;
        JPanel general = new JPanel();
        general.setLayout(new FlowLayout(FlowLayout.LEFT));
        String numofworkers = "Number of Workers: "+num;
        JLabel label = new JLabel(numofworkers);

        for(int i=1; i<num+1; i+=1)
        {
            createWorker(general,i);
        }
        general.add(label);
        add(general);
        
        
    }
    public void createWorker(JPanel general, int num) {
        String name = "Worker "+num;
        JButton worker = new JButton(name);
        worker.setBackground(Color.WHITE);
        if(num == 1) worker.setBackground(Color.GREEN);
        
        //updateStatus(worker,num);
        general.add(worker);
 
        worker.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent event){
               createFrame(num,worker);
           } 
        });
    }
    public void updateStatus(JButton worker, int i){
        int stat= client.workers[i].status;
        switch (stat){
            case 0: 
                worker.setBackground(Color.RED);
                JFrame CRIT = new JFrame();
                CRIT.setAutoRequestFocus(true);
                CRIT.setAlwaysOnTop(true);
                JPanel HELP = new JPanel();
                String stringer = "CRITICAL, HELP WORKER "+i;
                JLabel ss=new JLabel(stringer);
                HELP.add(ss);
                CRIT.getContentPane().add(BorderLayout.CENTER, HELP);
                CRIT.pack();
                CRIT.setLocationByPlatform(true);
                CRIT.setVisible(true);
                CRIT.setResizable(false);   
                
            break;    
            case 1: 
                worker.setBackground(Color.ORANGE);
            break;
            case 2:
            	worker.setBackground(Color.GREEN);
                break;
            case 3:
                worker.setBackground(Color.WHITE);
            break;
                
        }
                
    }
    public void createFrame(int num, JButton worker){
         EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	
                JFrame frame = new JFrame("Worker Info");
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4,2));
                panel.setOpaque(true);
                Font f= new Font("Courier",Font.BOLD,14);
                JLabel l1=new JLabel("Pulse: ");
                JLabel l2=new JLabel("Body Temperature: ");
                JLabel l3=new JLabel("Fall detector: ");
                JLabel l4=new JLabel("Conciousness: ");
                
                if (num == client.workers[num].id){
                String pulser= client.workers[num].pulse + "";
                String bodytemper= client.workers[num].temp + "";
                String fallinfor= client.workers[num].acc + "";
                String concinfor= client.workers[num].tf + "";
                if(client.workers[1].status==0){
                	
                        pulser= client.workers[num].pulse+59 + "";
                        bodytemper= client.workers[num].temp+10 + "";
                        fallinfor= client.workers[num].acc + "";
                        concinfor= client.workers[num].tf + "";
                	worker.setBackground(Color.RED);
                	try {
						Thread.currentThread().sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                
                JLabel pulseinfo= new JLabel(pulser);
                JLabel bodytempinfo= new JLabel(bodytemper);
                JLabel fallinfo= new JLabel(fallinfor);
                JLabel concinfo= new JLabel(concinfor);
                
                
                l1.setFont(f);
                l2.setFont(f);
                l3.setFont(f);
                l4.setFont(f);
                
                panel.add(l1);
                panel.add(pulseinfo);
                panel.add(l2);
                panel.add(bodytempinfo);
                panel.add(l3);
                panel.add(fallinfo);
                panel.add(l4);
                panel.add(concinfo);
                }
                else
                {
                	
                    JLabel pulseinfo= new JLabel("0");
                    JLabel bodytempinfo= new JLabel("0");
                    JLabel fallinfo= new JLabel("0");
                    JLabel concinfo= new JLabel("0");
                    
                    
                    l1.setFont(f);
                    l2.setFont(f);
                    l3.setFont(f);
                    l4.setFont(f);
                    
                    panel.add(l1);
                    panel.add(pulseinfo);
                    panel.add(l2);
                    panel.add(bodytempinfo);
                    panel.add(l3);
                    panel.add(fallinfo);
                    panel.add(l4);
                    panel.add(concinfo);
                }
                
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);               
           }
        });
    }
} 