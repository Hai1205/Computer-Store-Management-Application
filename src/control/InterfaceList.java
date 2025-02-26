package control;

public interface InterfaceList<T> {
    int n = 0;

    int length();

    void input();

    void display();

    void search();

    void writeToFile(boolean dontDeleteData);

    void readFromFile();

    void add();

    void add(T x);

    void remove();

    void remove(int index);

    void edit();

    void edit(int index);
}