MAIN=src/main.cpp
SRCS := $(filter-out $(MAIN),$(wildcard src/*.cpp))
CFLAGS=-g -I src/
.PHONY: clean test all

all:
	g++ -o sll-test $(CFLAGS) ${SRCS} ${MAIN}

run: all
	./sll-test

%test:
	cxxtestgen --runner=ErrorPrinter -o test/test-runner.cpp test/$@.cpp ${SRCS}
	g++ ${CFLAGS} test/test-runner.cpp $(SRCS) -o test-runner
	./test-runner

clean:
	rm -rf sll-test test/test-runner.cpp test-runner
