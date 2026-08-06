import java.util.*;
class RobotMessageTracker {

    class MessageNode {
        long timeStamp;
        String message;
        String status;
        
        MessageNode(long timeStamp, String message) {
            this.timeStamp = timeStamp;
            this.message = message;
            this.status = "ON_HOLD";
        }
    }

    public Map<String, MessageNode> messageTracker;
    public Queue<MessageNode> queue;

    public RobotMessageTracker() {
        messageTracker = new HashMap<>();
        queue = new LinkedList<>();
    }

    public void shouldPrintMessage(long timeStamp, String message){
        
        MessageNode newNode = new MessageNode(timeStamp, message);
        MessageNode prevNode = messageTracker.getOrDefault(message, null);
        
        if (prevNode != null && (newNode.timeStamp - prevNode.timeStamp) < 10) {
            prevNode.status = "CANCELLED";
            newNode.status = "CANCELLED";
        }
        messageTracker.put(message,  newNode);
        queue.offer(newNode);       
        flushQueue(timeStamp);
    }

    private void flushQueue(long timeStamp) {
        
        while (!queue.isEmpty() && (timeStamp - queue.peek().timeStamp) >= 10) {
            MessageNode node = queue.poll();
            if(node.status.equals("ON_HOLD")){
                System.out.println(node.message + " " + node.timeStamp);
            }    
        } 
    }

    

}