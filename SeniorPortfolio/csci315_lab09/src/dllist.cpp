#ifdef DLLIST_HPP



template<class T>

DLList<T>::DLList() {

    mTail = mHead = NULL;

    mSize = 0;

}



/* Do a deep copy of dll into the this.

 * Note: This one uses a reference to a Doubly Linked List!

 */

template<class T>

DLList<T>::DLList(const DLList<T> &dll) {

    mTail = mHead = NULL;

    mSize = 0;

   //dll->mPrev = NULL;


    for (Node *rover = dll.mHead; rover != NULL; rover = rover->mNext) {

       	 append(rover->mData);

    }

}



//deletes the list
template<class T>

DLList<T>::~DLList() {

   Node *rover ;


    for (rover->mPrev = NULL, rover = mHead;
	 rover != NULL; rover = rover->mNext) {

        if (rover->mPrev != NULL) {

            delete rover->mPrev;
 
       }

        rover->mPrev = rover;

    }


    if (mTail != NULL) {

        delete mTail;

    }


    mTail = mHead = NULL;

    mSize = 0;


}



//adds to the list

template<class T>

bool DLList<T>::append(const T &val) {

//	Node *rov;

    Node *node = new Node();

    node->mData = val;

    node->mNext = NULL; // AKA zero.



    // Take care of the initial case.

    if (mHead == NULL && mTail == NULL) {

        mHead = node;

        mTail = node;

	node->mPrev = NULL;

        mSize++;

        return true;

    }



    node->mPrev = mTail;


    mTail->mNext = node;

    mTail = node;

    mSize++;

    return true;

}



//prints the list

template<class T>

void DLList<T>::print() const {

    for (Node *rover = mHead; rover != NULL; rover = rover->mNext) {

        std::cout << rover->mData << "(" << rover->mNext << "), ";

    }

    std::cout << "\n";

}



/* Return the current length of the Doubly Linked List */

template<class T>

int DLList<T>::getLength() const {

    return mSize;

}


/* Insert val at position pos.

 * Return true if successful (it can be placed.)

 * Otherwise return false.

 */

template<class T>

bool DLList<T>::insert(const int pos, const T &val) {

   if (pos < 0 || pos > mSize) { return false;}


    Node *rover = mHead;

    Node *node = new Node();

    node->mData = val;

    node->mNext = NULL;


    for (int i = 0; i < pos; i++,rover = rover->mNext) {

	    rover->mPrev = rover;

    }


   // for (int i = 0; i < (pos-1); i++, mPrev = rover)(;)


    if(rover != NULL){

	    node->mNext = rover;

	    rover->mPrev = node;

    }


    if(rover->mPrev != NULL){

	    rover = node;

	    node->mPrev = rover->mPrev;

    }else{

	    mHead = node;

	    node->mPrev = NULL;

    }


    if(mTail == NULL){

	    mTail = node;

    }

    if(node->mNext == NULL){

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

bool DLList<T>::remove(const T &val) {

    Node *rover, *delme;


    // Take care of case where mHead is removed!

    if (mHead != NULL && mHead->mData == val) {

        delme = mHead;

        mHead = mHead->mNext;

        delete delme;


        if (mHead == NULL) {

            mTail = NULL;

	    //rover->mPrev = NULL;

        }


	mSize--;

        return true;

    }


    for (rover = mHead; rover->mNext != NULL && rover->mNext->mData != val;
	 rover->mPrev = rover, rover = rover->mNext){;}


    // val was not found!

    if (rover->mData != val) {

        return false;

    }


    delme = rover->mNext;

    rover->mNext = rover->mNext->mNext;


    if (delme == mTail) {

        mTail = rover;

    }


    mSize--;

    delete delme;

    return true;

}



//put data in at a spot

template<class T>

T& DLList<T>::operator[](const int pos) const {

    Node *rover = mHead;


    // Move rover over pos times.

    for (int i = 0; i < pos; i++, rover->mPrev = rover, rover = rover->mNext){;}


    return rover->mData;

}



//checks if the lists are equal

template<class T>

bool DLList<T>::operator==(const DLList<T> &list) const {

    Node *rover1, *rover2;


   // if(mSize != list.getLength()){return false;}

    
    for (rover1 = mHead, rover2 = list.mHead;

            rover1 != NULL && rover2 != NULL && rover1->mData == rover2->mData;

           rover1->mPrev = rover1, rover1 = rover1->mNext,

	  rover2->mPrev = rover2, rover2 = rover2->mNext){;}


    return rover1 == NULL && rover2 == NULL;

}



#endif
