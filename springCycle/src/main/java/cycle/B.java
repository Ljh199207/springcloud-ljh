package cycle;

public class B {

    private A a;

    /*public B(A a) {
        this.a = a;
    }*/

    public void cycleB() {
        a.cycleA();
    }

    public void setA(A a) {
        this.a = a;
    }
}
