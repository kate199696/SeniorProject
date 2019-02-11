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
            SLList<int> sll2(sll);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            for (int i = 0; i < sll.getLength(); i++) {
                TS_ASSERT(sll[i] == sll2[i]);
            }
            TS_ASSERT(sll == sll2);
        }
        void test2(void) {
            /* Fill in some test cases here for cxx test gen */
            SLList<int> sll;
            SLList<int> sll2(sll);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            for (int i = 0; i < sll.getLength(); i++) {
                TS_ASSERT(sll[i] == sll2[i]);
            }
            TS_ASSERT(sll == sll2);
        }
        void test3(void) {
            /* Fill in some test cases here for cxx test gen */
            SLList<int> sll;
            sll.append(34);
            SLList<int> sll2(sll);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            for (int i = 0; i < sll.getLength(); i++) {
                TS_ASSERT(sll[i] == sll2[i]);
            }
            TS_ASSERT(sll == sll2);
        }
        void test4(void) {
            /* Fill in some test cases here for cxx test gen */
            SLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            SLList<int> sll2(sll);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            for (int i = 0; i < sll.getLength(); i++) {
                TS_ASSERT(sll[i] == sll2[i]);
            }
            sll2.remove(66);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            TS_ASSERT(sll == sll2);
            sll2.remove(54);
            TS_ASSERT(!(sll == sll2));
        }
        void test11(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            DLList<int> sll2(sll);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            for (int i = 0; i < sll.getLength(); i++) {
                TS_ASSERT(sll[i] == sll2[i]);
            }
            TS_ASSERT(sll == sll2);
        }
        void test12(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            DLList<int> sll2(sll);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            for (int i = 0; i < sll.getLength(); i++) {
                TS_ASSERT(sll[i] == sll2[i]);
            }
            TS_ASSERT(sll == sll2);
        }
        void test13(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            DLList<int> sll2(sll);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            for (int i = 0; i < sll.getLength(); i++) {
                TS_ASSERT(sll[i] == sll2[i]);
            }
            TS_ASSERT(sll == sll2);
        }
        void test14(void) {
            /* Fill in some test cases here for cxx test gen */
            DLList<int> sll;
            sll.append(34);
            sll.append(74);
            sll.append(44);
            sll.append(54);
            sll.append(134);
            DLList<int> sll2(sll);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            for (int i = 0; i < sll.getLength(); i++) {
                TS_ASSERT(sll[i] == sll2[i]);
            }
            sll2.remove(66);
            TS_ASSERT(sll.getLength() == sll2.getLength());
            TS_ASSERT(sll == sll2);
            sll2.remove(54);
            TS_ASSERT(!(sll == sll2));
        }
};

