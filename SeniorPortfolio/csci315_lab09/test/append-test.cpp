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
            TS_ASSERT(sll[1] == 74);
            TS_ASSERT(sll[0] == 34);
            TS_ASSERT(sll[2] == 44);
        }
        void test2(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            TS_ASSERT(sll[1] == 74);
            TS_ASSERT(sll[0] == 34);
            TS_ASSERT(sll[2] == 44);
        }
};

