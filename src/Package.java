public class Package {
    public char name;
    public boolean isSent;

    public Package(char name) {
        this.name = name;
        this.isSent = false;
    }
    public void display(){
        System.out.print(this.name);
    }
}
