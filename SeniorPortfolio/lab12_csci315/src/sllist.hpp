#ifndef SLLIST_HPP
#define SLLIST_HPP

/* SLL = Singly Linked List */
template<class T>
class SLList {
    private:
        /* Class exercise to fill in. */
        class Node {
            public:
                T mData;
                Node *mNext;
        };

        Node *mHead, *mTail;
        int mSize;
    public:

        /* Empty constructor shall create an empty Linked List! */
        SLList();

        /* Do a deep copy of sll into the this.
         * Note: This one uses a reference to a Singly Linked List!
         */
        SLList(const SLList<T> &sll);

        /* Deconstructor shall free up memory */
        ~SLList();

        /* Return the current length of the Singly Linked List */
        int getLength() const;

        /* Insert at the end of the list.*/
        bool append(const T &val);

        /* Insert val at position pos.
         * Return true if successful (it can be placed.)
         * Otherwise return false.
         */
        bool insert(const int pos, const T &val);

        /* Print out the Singly Linked List */
        void print() const;

        /* Remove the first instance of val
         * Return true if found and removed.
         * Otherwise return false.
         */
        bool remove(const T &val);

        /* Retrieves the element at position pos */
        T& operator[](const int pos) const;

        /* Returns if the two lists contain the same elements in the
         * same order.*/
        bool operator==(const SLList<T> &list) const;

        /* Sort the linked list.  You may use any sort algorithm you wish */
        void sort();
};

/* Since SLList is templated, we include the .cpp
 * Templated classes are not generated until utilized (or explicitly declared.)
 */

#include "sllist.cpp"

#endif
