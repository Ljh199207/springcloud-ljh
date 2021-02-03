package cycle;

public class A {

    private B b;

  /*  public A(B b) {
        this.b = b;
    }*/

    public void cycleA() {
        b.cycleB();
    }

    public void setB(B b) {
        this.b = b;
    }
}
