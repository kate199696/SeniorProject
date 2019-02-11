MAIN=src/main.cpp
SRCS := $(filter-out $(MAIN),$(wildcard src/*.cpp))
CFLAGS=-g -I src/
.PHONY: clean test all
BIN=sort-test

all:
	g++ -o ${BIN} $(CFLAGS) ${SRCS} ${MAIN}

run: all
	./${BIN}

%test:
	cxxtestgen --runner=ErrorPrinter -o test/test-runner.cpp test/$@.cpp ${SRCS}
	g++ ${CFLAGS} test/test-runner.cpp $(SRCS) -o test-runner
	./test-runner

clean:
	rm -rf ${BIN} test/test-runner.cpp test-runner
