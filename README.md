# Mark Harrison - Cake Solutions
# Example blob finder implementation

##Design
To enable a more iterative style of development I decided to use implicits (A simplified use of ["Parfait"](https://t.co/GE1nIp5fx3)) to allow different "blob finder" algorithms to be plugged in. This is (imho) a better alternative to the [Cake pattern](http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth) because in more complex implementations it results in less boilerplate and simpler syntax.

A Blob is a shape in two-dimensional integer coordinate space where all cells have at least one adjoining cell to the right, left, top, or bottom that is also occupied. This is represented by a grid of 1's and 0's, where each blob `Cell` is represented by a 1.

The aim is to find the outside edges of of the blob, as in the outermost top, bottom, left and right points of the blob. All state is defined in the `State` object. The current known edges are stored in the `SolutionState` class. Whereas other information relating to the current search (visited 1 and 0 cells and the total `Cells` visited) is represented by the `SearchState` class. `GlobalState` wraps these classes. All state is immutable.

We have a number of `CellPicker` implementations, each of which defines a different algorithm for finding the next `Cell` to visit.

* `RandomPicker` - This simply finds any random `Cell` that is outside our current known boundaries.
* `NeighboursPicker` - This makes use of the fact that we know all blob `Cells` must be connected to at least one other blob `Cell`. We take the list of all visited blob `Cells` and find the unvisited neighbours of each `Cell`. We then find one `Cell` from this list that is either on or outside the current known boundaries of the blob.

The `Grid` class defines a `Map((Int, Int) -> Boolean)` which represents the position and state of a `Cell` in the grid. We also store the width and height of the grid to help optimise the `CellPicker` algorithms.

The `GridLoader` trait is used to allow us to provide different implementations for loading the Grid. We only provide one here, the `FileGridLoader`, which loads the grid from a plain text file - grid.txt.

The `BlobFinder` class is responsible for linking everything together, the class requires implicit implementations of `CellPicker` and `GridLoader` to be provided. The flow is as follows:

* The class recursively searches for `Cells` using the provided `CellPicker`.
* The state of each `Cell` is checked against the grid.
* We create a new `GlobalState` for the search, updating the boundaries, visited cells (either blob or zero), and the number of reads.
* The search ends when the `CellPicker` can't find any more viable `Cells`.

##Instructions
###Pre-requisites
Ensure, both SBT and Java are installed and configured.

###Compiling
sbt compile

###Running (Will provide results for all `CellPicker` implementations with average reads and boundaries, the example grid is loaded from grid.txt)
sbt test

Note that sbt run will not provide any useful results, only to recommend that you run the provided tests.


