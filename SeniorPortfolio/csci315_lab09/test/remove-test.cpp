#include <cxxtest/TestSuite.h>
#include <limits.h>
#include <stdio.h>
#include "sllist.hpp"
#include "dllist.hpp"

class MyTestSuite1 : public CxxTest::TestSuite {
    public:
        void test1(void) {
            /* Fill in some test cases here for cxx test gen */
            SLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.remove(74) == true);
            TS_ASSERT(sll[1] == 44);
            TS_ASSERT(sll[2] == 54);
        }
        void test2(void) {
            SLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.getLength() == 5);
            TS_ASSERT(sll.remove(9999) == false);
            TS_ASSERT(sll[1] == 74);
        }
        void test3(void) {
            SLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.getLength() == 5);
            TS_ASSERT(sll.remove(134) == true);
            TS_ASSERT(sll[0] == 34);
            TS_ASSERT(sll[3] == 54);
            TS_ASSERT(sll.getLength() == 4);
        }
        void test4(void) {
            SLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.remove(34) == true);
            TS_ASSERT(sll[0] == 74);
            TS_ASSERT(sll[1] == 44);
        }
        void test11(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.remove(74) == true);
            TS_ASSERT(sll[1] == 44);
            TS_ASSERT(sll[2] == 54);
        }
        void test12(void) {
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.getLength() == 5);
            TS_ASSERT(sll.remove(9999) == false);
            TS_ASSERT(sll[1] == 74);
        }
        void test13(void) {
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.getLength() == 5);
            TS_ASSERT(sll.remove(134) == true);
            TS_ASSERT(sll[0] == 34);
            TS_ASSERT(sll[3] == 54);
            TS_ASSERT(sll.getLength() == 4);
        }
        void test14(void) {
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.remove(34) == true);
            TS_ASSERT(sll[0] == 74);
            TS_ASSERT(sll[1] == 44);
        }
};

