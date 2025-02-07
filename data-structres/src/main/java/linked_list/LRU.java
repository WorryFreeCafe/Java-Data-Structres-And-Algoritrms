package linked_list;


/**
 * 我的思路是这样的：我们维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。
 * 当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
 * 1.如果此数据之前已经被缓存在连表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
 * 2.如果此数据没有在缓存链表中，又可以分为两种情况：
 * ·如果此时缓存未满，则将此结点直接插入到链表的头部：
 * ·如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
 */
public class LRU {
    private class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private int capacity;
    private Node head;
    private Node tail;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.key == key) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                    current.next = head;
                    head = current;
                }
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.key == key) {
                current.value = value;
                if (previous != null) {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                    current.next = head;
                    head = current;
                }
                return;
            }
            previous = current;
            current = current.next;
        }

        Node newNode = new Node(key, value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        if (size() > capacity) {
            if (tail != null) {
                Node temp = tail;
                tail = findPrevious(tail);
                if (tail != null) {
                    tail.next = null;
                }
                temp.next = null;
            }
        }
    }

    private int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    private Node findPrevious(Node node) {
        Node current = head;
        while (current != null && current.next != node) {
            current = current.next;
        }
        return current;
    }

    public static void main(String[] args) {
        LRU cache = new LRU(3); // 创建一个容量为2的LRU缓存

        // 测试put方法
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Cache after putting (1,1) and (2,2):");
        printCache(cache);

        // 测试get方法
        System.out.println("Get value of key 1: " + cache.get(1)); // 返回1
        System.out.println("Cache after getting key 1:");
        printCache(cache);

        // 测试put方法，触发淘汰机制
        cache.put(3, 3);
        System.out.println("Cache after putting (3,3):");
        printCache(cache);

        // 测试get方法，访问不存在的key
        System.out.println("Get value of key 2: " + cache.get(2)); // 返回-1 (未找到)
        System.out.println("Cache after getting key 2:");
        printCache(cache);

        // 测试put方法，更新已存在的key
        cache.put(1, 4);
        System.out.println("Cache after putting (1,4):");
        printCache(cache);

        // 测试get方法，访问更新后的key
        System.out.println("Get value of key 1: " + cache.get(1)); // 返回4
        System.out.println("Cache after getting key 1:");
        printCache(cache);
    }

    private static void printCache(LRU cache) {
        Node current = cache.head;
        while (current != null) {
            System.out.print("(" + current.key + "," + current.value + ") ");
            current = current.next;
        }
        System.out.println();
    }

}
