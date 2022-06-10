public class ArrayDeque<T>{
    private T[] arr;
    private int pre_size;
    private int post_size;
    private int size;
    private int Capacity;
    int first_idx;
    int last_idx;

    private T[] resize(){
        int new_Capacity = 2*Capacity;
        T[] new_arr = (T[]) new Object[new_Capacity];
        ArrayCopy(arr, new_arr);
        first_idx = ((new_Capacity - 1) >> 1) - pre_size;
        Capacity = new_Capacity;
        last_idx = first_idx+size+1;
        return new_arr;
    }

    private void ArrayCopy(T[] from, T[] to){
        // int newCap = to.length;
        int start_idx = ((Capacity-1) >> 1) + 1;
        int end_idx = start_idx + Capacity - 1;
        for (int i = start_idx; i <= end_idx; i++){
            to[i] = from[i-start_idx];
        }
    }
    public void addFirst(T item){
        if(size == ((Capacity-1)>>1)+1) {
            arr = resize();
        }
        arr[first_idx] = item;
        first_idx--;
        pre_size++;
        size = pre_size + post_size;
    }
    public void addLast(T item){
        if(size == ((Capacity-1)>>1)+1) {
            arr = resize();
        }
        arr[last_idx] = item;
        last_idx++;
        post_size++;
        size = pre_size + post_size;


    }
    public T removeFirst(){
        T temp = arr[first_idx+1];
        arr[first_idx+1] = null;
        return temp;
    }
    public T removeLast(){
        T temp = arr[last_idx-1];
        arr[last_idx-1] = null;
        return temp;
    }
    public ArrayDeque(){
        Capacity = 8;
        arr = (T[]) new Object[Capacity];
        size = 0;
        first_idx = (Capacity-1)>>1;
        last_idx = first_idx+1;
    }
    /*
    public ArrayDeque(ArrayDeque<T> other){

    }

     */
    public T get(int idx){
        if(this.isEmpty()){return null;}
        return arr[idx+first_idx+1];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public void printArray(){
    }

}