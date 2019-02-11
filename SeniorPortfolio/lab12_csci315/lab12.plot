set terminal postscript color
set output "lab_graphs.ps"
set ylabel "time(seconds)"
set xlabel "Data Size"
plot [:][:] "selectionOut" using 1:2 with line title "Selection Sort", "insertionOut" using 1:2 with line title "Insertion Sort", "bubbleOut" using 1:2 with line title "Bubble Sort", "sllOut" using 1:2 with line title "SinglyLinkedList"
