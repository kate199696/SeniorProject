/* Array is templated, therefore only include the code if it is included from
 * the header file! 
 */
#ifdef ARRAY_H
#include <stdio.h>

using namespace std;

/* Do a deep copy of the array into the list.
 * Note: This one uses a pointer!
 */
template <class T>
Array<T>::Array(const T *array, const int size){
	mSize = size;
	arr = new T[size];
	for(int i = 0; i < size; i++){
		arr[i] = array[i];
	}
}

/* Do a deep copy of the array into the list
 * Note: This one uses a reference to a List!
 */
template <class T>
Array<T>::Array(const Array<T> &list){
	for(int i =0; i<mSize; i++){
		arr[i] = list[i];
	}
}
template <class T>
int Array<T>::getLength() const{
	return mSize;
}

template <class T>
int Array<T>::indexOf(const T &value){
	for(int i = 0; i < mSize; i++){
		if(arr[i] == value)
			return i;
	}
	return -1;
}

template <class T>
bool Array<T>::remove(const int index){
	if(mSize == 0 || index >= mSize){
		return false;
	}
	for(int i = index; i < mSize; i++){
		arr[i] = arr[i+1];
	}
	mSize--;
	//test to see if the index is removed
	if(index >= 0 && index < mSize){
		return true;
	}

	return false;
}

/* Retrieves the element at position pos */
template <class T>
T& Array<T>::operator[](const int pos){
	return arr[pos];
}

template <class T>
bool Array<T>::operator==(Array<T> &list)const{
	if(mSize != list.getLength()){
		return false;
	}
	for(int i = 0; i < mSize; i++){
		if(arr[i] != list[i]){
			return false;
		}
	}
	
	return true;
}

template <class T>
void Array<T>::bubbleSort(){
	for(int j = 0; j < mSize; j++){
	    for(int i = 0; i < mSize-1-j; i++){
		if(arr[i] > arr[i+1]){
			int temp = arr[i];
			arr[i] = arr[i+1];
			arr[i+1] = temp;
		}
	    }
	}
}

template <class T>
void Array<T>::insertionSort() {
    for (int i = 1; i < mSize; i++) {
        int nextPosition = 0; // Next position for numbers[i]

        // Find the position to insert numbers[i]
        for (int j = 0; j < i; j++) {
            if (arr[j] < arr[i]) {
                nextPosition++;
            }
        }

        //Save the value
        int tmp = arr[i];

        // Shift all value down
        for (int j = i; j > nextPosition; j--) {
            arr[j] = arr[j-1];
        }

        arr[nextPosition] = tmp;
    }
}

template <class T>
void Array<T>::selectionSort(){
	int lowest = arr[0];
	int temp = lowest;
	for(int i = 0; i < mSize-1; i++){
		lowest = i;

		for(int j = i+1; j < mSize; j++){
			if(arr[j] < arr[lowest]){
				lowest = j;
			}
		}
		temp = arr[i];
		arr[i] = arr[lowest];
		arr[lowest] = temp;

	}
}

template <class T>
void Array<T>::sort(){
	insertionSort();
}

template <class T>
void Array<T>::print(){
	for(int i = 0; i < mSize; i++){
		cout << arr[i] << ", ";
	}
}

template <class T>
Array<T>::~Array() {
	delete[] arr;
}

#endif
