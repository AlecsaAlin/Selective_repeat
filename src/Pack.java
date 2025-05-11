public class Pack {
    public int data;
    public boolean isSent;

    public Pack(int data) {
        this.data = data;
        this.isSent = false;
    }
    public void display(){

        System.out.print(this.data);
    }
}
