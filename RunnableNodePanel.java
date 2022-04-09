/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class RunnableNodePanel implements Runnable{
    private PanelNode nodePanel = null;
     private boolean isFinished=false;
    public RunnableNodePanel(PanelNode pn) {
        this.nodePanel = pn;
    }

    
    
    @Override
    public void run() {
        try {
  //      while(!Thread.currentThread().isInterrupted()&& isFinished == false){
                switch(FrameNode.actionode){
                    case INSERT:
                        nodePanel.doInsert(FrameNode.tmp);
                        break;
                    case DELETE:
                        nodePanel.doDelete(FrameNode.tmp);
                        break;
                    case UPDATE:
                        //nodePanel.doUpdate();
                        nodePanel.doUpdate(FrameNode.tmp, FrameNode.upd);
                        break;
                    case NLR:
                        nodePanel.doNLR();
                        break;
                    case LNR:
                        nodePanel.doLNR();
                        break;
                    case LRN:
                        nodePanel.doLRN();
                        break;
                    case SEARCH:
                        nodePanel.doSearch(FrameNode.tmp);
                        break;
                    case MIN:
                        nodePanel.doMin();
                        break;
                    case MAX:
                        nodePanel.doMax();
                        break;
                }
 //       }
        Thread.sleep(1000);
        
        } catch (InterruptedException ex) {
            //Logger.getLogger(RunnableNodePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
