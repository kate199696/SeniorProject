#include <stdlib.h>
#include <iostream>
#include <time.h>
#include "array.hpp"
#include "sllist.hpp"

using namespace std;

#define SIZE (10000)
//#define MAX_INT_SIZE (100)

int main(int argc, char *argv[]){
	clock_t start,diff;
	double elapsedTime;
	int *numbers = new int[SIZE];
	int *originalSet = new int[SIZE];
	srand(100);

	for(int i = 0; i< SIZE; i++){
		numbers[i] = rand();
		originalSet[i] = numbers[i];
	}

/*	Array<int> ary(numbers,1000);
//	ary.print();
	//cout << ary.getLength();
	cout << "\n" << "\n" << "\n";
	//cout << "Before \n";
	start = clock();
	ary.selectionSort();
	diff = clock() - start;
	elapsedTime - diff * 1.0 / CLOCKS_PER_SEC;
	cout << "elapsed time : " << elapsedTime << "\n";
	//cout << "HERE" << "\n";
	//ary.print();
*/
	
	for(int n = 100; n < SIZE; n+= 100){
		Array<int> ary(numbers,n);
		start = clock();
		ary.selectionSort();
		diff = clock() - start;
		elapsedTime = diff * 1.0 / CLOCKS_PER_SEC;
		cout << n << " " << elapsedTime << "\n";


		cout << flush;
	}

	
	return 0;
}
