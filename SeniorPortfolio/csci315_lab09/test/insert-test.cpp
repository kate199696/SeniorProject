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
            TS_ASSERT(sll.insert(1, 1234) == true);
            TS_ASSERT(sll[1] == 1234);
            TS_ASSERT(sll[2] == 74);
        }
        void test2(void) {
            /* Fill in some test cases here for cxx test gen */
            SLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.insert(-1, 1234) == false);
            TS_ASSERT(sll[1] == 74);
        }
        void test3(void) {
            /* Fill in some test cases here for cxx test gen */
            SLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.insert(0, 1234) == true);
            TS_ASSERT(sll[0] == 1234);
            TS_ASSERT(sll[1] == 34);
        }
        void test4(void) {
            /* Fill in some test cases here for cxx test gen */
            SLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.insert(5, 1234) == true);
            TS_ASSERT(sll[5] == 1234);
            TS_ASSERT(sll[4] == 134);
        }
        void test11(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.insert(1, 1234) == true);
            TS_ASSERT(sll[1] == 1234);
            TS_ASSERT(sll[2] == 74);
        }
        void test12(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.insert(-1, 1234) == false);
            TS_ASSERT(sll[1] == 74);
        }
        void test13(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.insert(0, 1234) == true);
            TS_ASSERT(sll[0] == 1234);
            TS_ASSERT(sll[1] == 34);
        }
        void test14(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll.insert(5, 1234) == true);
            TS_ASSERT(sll[5] == 1234);
            TS_ASSERT(sll[4] == 134);
        }
};

