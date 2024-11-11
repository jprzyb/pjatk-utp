package zad2;
public class StringTask implements Runnable{
    public enum TaskState {CREATED, RUNNING, ABORTED, READY}
    String a;
    String result;
    int i;
    TaskState  state;
    Thread thread;
    public StringTask(String a, int i) {
        this.a = a;
        this.i = i;
        this.state = TaskState.CREATED;
        this.result = "";
        this.thread = new Thread(this);
    }

    public TaskState getState() {
        return state;
    }

    @Override
    public void run() {
        state = TaskState.RUNNING;
        for(int k=0;k<i;k++){
            if(thread.isInterrupted()){
                break;
            }
            result+=a;
        }
        if(!state.equals(TaskState.ABORTED)) state =TaskState.READY;
    }
    public void abort(){
        state = TaskState.ABORTED;
        thread.interrupt();
    }
    public void start(){
        thread.start();
    }
    public boolean isDone(){
        if(state.equals(TaskState.READY) || state.equals(TaskState.ABORTED)) return true;
        return false;
    }
    public String getResult(){
        return result;
    }
}