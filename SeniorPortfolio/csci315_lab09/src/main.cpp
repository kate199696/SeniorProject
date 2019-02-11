#include <stdlib.h>

#include <iostream>

#include "sllist.hpp"



using namespace std;


int main(int argc, char *argv[]) {

    /* You may write manual tests here. */

    SLList<int> sll;

    sll.append(34);

    sll.print();

    sll.append(54);

    sll.print();

    cout << "The length is " << sll.getLength() << "\n";

    sll.append(74);

    sll.append(105);

    sll.append(24);

    sll.append(85);

    sll.append(45);

    sll.append(98);

    sll.append(12);

    cout << "The length is " << sll.getLength() << "\n";

    sll.print();

    cout << "My element at index 1 is:" << sll[1] << "\n";

    cout << "My element at index 5 is:" << sll[5] << "\n";

    cout << "My element at index 2 is:" << sll[2] << "\n";

}

