#ifndef DLLIST_HPP

#define DLLIST_HPP


/* DLL = Doubly Linked List */

template<class T>

class DLList {

    private:

        /* Class exercise to fill in. */

        class Node {

            public:

                T mData;

                Node *mNext, *mPrev;

        };


        Node *mHead, *mTail;

        int mSize;

    public:


        /* Empty constructor shall create an empty Linked List! */

        DLList();


        /* Do a deep copy of sll into the this.

         * Note: This one uses a reference to a Doubly Linked List!

         */

        DLList(const DLList<T> &dll);


        /* Deconstructor shall free up memory */

        ~DLList();


        /* Return the current length of the Doubly Linked List */

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

        bool operator==(const DLList<T> &list) const;

};


/* Since SLList is templated, we include the .cpp

 * Templated classes are not generated until utilized (or explicitly declared.)

 */


#include "dllist.cpp"


#endif
