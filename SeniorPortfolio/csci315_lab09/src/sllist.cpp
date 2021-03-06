#ifdef SLLIST_HPP



template<class T>

SLList<T>::SLList() {

    mTail = mHead = NULL;

    mSize = 0;

}


/* Do a deep copy of sll into the this.

 * Note: This one uses a reference to a Singly Linked List!

 */

template<class T>

SLList<T>::SLList(const SLList<T> &sll) {

    mTail = mHead = NULL;

    mSize = 0;


    for (Node *rover = sll.mHead; rover != NULL; rover = rover->mNext) {

        append(rover->mData);

    }

}

//deletes the list


template<class T>

SLList<T>::~SLList() {

    Node *rover, *prev;

    for (prev = NULL, rover = mHead; rover != NULL; rover = rover->mNext) {

        if (prev != NULL) {

            delete prev;

        }

        prev = rover;

    }

    if (prev != NULL) {

        delete prev;

    }

    mTail = mHead = NULL;

    mSize = 0;

}

//adds to the list


template<class T>

bool SLList<T>::append(const T &val) {

    Node *node = new Node();

    node->mData = val;

    node->mNext = NULL; // AKA zero.


    // Take care of the initial case.

    if (mHead == NULL && mTail == NULL) {

        mHead = node;

        mTail = node;

        mSize++;

        return true;

    }


    mTail->mNext = node;

    mTail = node;

    mSize++;

    return true;


}


//Prints to the list


template<class T>

void SLList<T>::print() const {

    for (Node *rover = mHead; rover != NULL; rover = rover->mNext) {

        std::cout << rover->mData << "(" << rover->mNext << "), ";

    }

    std::cout << "\n";

}



/* Return the current length of the Singly Linked List */

template<class T>

int SLList<T>::getLength() const {

    return mSize;

}



/* Insert val at position pos.

 * Return true if successful (it can be placed.)

 * Otherwise return false.

 */

template<class T>

bool SLList<T>::insert(const int pos, const T &val) {

    if (pos < 0 || pos > mSize) { return false;}


    Node *rover = mHead;

    Node *prev = NULL;

    Node *node = new Node();

    node->mData = val;

    node->mNext = NULL;


    for (int i = 0; i < pos; i++, rover = rover->mNext) {

        prev = rover;

    }


    if (rover != NULL) {

        node->mNext = rover;

    }


    if (prev != NULL) {

        prev->mNext = node;

    } else {

        mHead = node;

    }


    if (mTail == NULL) {

        mTail = node;

    }


    if (node->mNext == NULL) {

        mTail = node;

    }


    mSize++;


    return true;

}



/* Remove the first instance of val

 * Return true if found and removed.

 * Otherwise return false.

 */

template<class T>

bool SLList<T>::remove(const T &val) {

     Node *rover, *delme;


    // Take care of case where mHead is removed!

    if (mHead != NULL && mHead->mData == val) {

        delme = mHead;

        mHead = mHead->mNext;

        delete delme;


        if (mHead == NULL) {

            mTail = NULL;

        }


        mSize--;


        return true;

    }


    for (rover = mHead; rover->mNext != NULL && rover->mNext->mData != val; rover = rover->mNext){;}


    // val was not found!

    if (rover->mNext == NULL) {

        return false;

    }


    delme = rover->mNext;

    rover->mNext = rover->mNext->mNext;


    if(rover == NULL){
	    return false;

    }

    if (delme == mTail) {

        mTail = rover;

    }


    mSize--;


    delete delme;

    return true;

}

//inserts data into a specific spot in the list


template<class T>

T& SLList<T>::operator[](const int pos) const {

    Node *rover = mHead;


    // Move rover over pos times.

    for (int i = 0; i < pos; i++, rover = rover->mNext){;}


    return rover->mData;

}

//check if lists are the same


template<class T>
bool SLList<T>::operator==(const SLList<T> &list) const {

    Node *rover1, *rover2;


    for (rover1 = mHead, rover2 = list.mHead;

            rover1 != NULL && rover2 != NULL && rover1->mData == rover2->mData;

            rover1 = rover1->mNext, rover2 = rover2->mNext){;}


    return rover1 == NULL && rover2 == NULL;

}


#endif
