package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


/*
    Like arrays, Linked List is a linear data structure.
    Unlike arrays, linked list elements are not stored at contiguous location; the elements are linked using pointers.
    Why Linked List?
        Arrays can be used to store linear data of similar types, but arrays have following limitations.
        1) The size of the arrays is fixed: So we must know the upper limit on the number of elements in advance.
            Also, generally, the allocated memory is equal to the upper limit irrespective of the usage.
        2) Inserting a new element in an array of elements is expensive,
            because room has to be created for the new elements and to create room existing elements have to shifted.
            For example, in a system if we maintain a sorted list of IDs in an array id[].
            id[] = [1000, 1010, 1050, 2000, 2040].
            And if we want to insert a new ID 1005, then to maintain the sorted order,
            we have to move all the elements after 1000 (excluding 1000).
            Deletion is also expensive with arrays until unless some special techniques are used.
            For example, to delete 1010 in id[], everything after 1010 has to be moved.
    Advantages over arrays
        1) Dynamic size
        2) Ease of insertion/deletion
    Drawbacks:
        1) Random access is not allowed. We have to access elements sequentially starting from the first node.
            So we cannot do binary search with linked lists.
        2) Extra memory space for a pointer is required with each element of the list.
*/

class ListNode {    
    int value;
    ListNode next;
    ListNode(int value) {
        this.value = value;
    }

    public ListNode next() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }

}




public class LinkedList {

    public ListNode insertAtHead(ListNode head, int value) {
        ListNode newNode = new ListNode(value);
        newNode.setNext(head);
        return newNode;
    }

    public void insertAfter(ListNode l, int data) {
        if(l ==  null)
            return;
        ListNode newNode = new ListNode(data);
        newNode.setNext(l.next());
        l.setNext(newNode);
    }

    public ListNode insertAtEnd(ListNode head, int data) {
        ListNode newNode = new ListNode(data);

        if(head == null) {
            return newNode;
        }

        ListNode cur = head;
        while(cur.next() != null) {
            cur = cur.next();
        }
        cur.setNext(newNode);
        return head;
    }

    /* You return because in case where it has only one node and
        that matches, the head should be null
     */
    public ListNode deleteNode(ListNode head, int data) {
        if(head == null)
            return head;

        if(head.getValue() == data) {
            head = head.next();
            return head;
        }

        ListNode cur = head;
        while (cur.next() != null) {
            if(cur.next().getValue() == data) {
                cur.setNext(cur.next().next());
                break;
            }
            cur = cur.next();
        }
        return head;
    }


    public int length(ListNode head) {
        ListNode temp = head;
        int count = 0;
        while(temp != null) {
            ++count;
            temp = temp.next();
        }
        return count;
    }

    public ListNode nthNodeFromEnd(ListNode head, int n) {
        ListNode temp = head;
        ListNode cur = head;
        int i = 0;

        while(cur != null) {
            ++i;
            if(i > n) {
                temp = temp.next();
            }
            cur = cur.next();
        }
        if(i < n) {
            return null;
        }
        return temp;
    }


    public void printList(ListNode head) {
        System.out.println("+++++++++++");
        while(head != null) {
            System.out.println(head.getValue());
            head = head.next();
        }
        System.out.println("===========");
    }
}
