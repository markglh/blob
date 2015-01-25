package com.example

import com.markglh.blob.grid.Grid.Cell

/**
 * Simple object to hold all the immutable state for the search.
 */
object State {

  /**
   * The complete state for both the search and solution.
   */
  case class GlobalState(searchState: SearchState = SearchState(), solutionState: Option[SolutionState] = None) {
    def this(searchState: SearchState, solutionState: SolutionState) = this(searchState, Some(solutionState))
  }

  object GlobalState {
    def apply(searchState: SearchState, solutionState: SolutionState): GlobalState =
      new GlobalState(searchState: SearchState, solutionState: SolutionState)
  }

  /**
   * Stores the current state of the search.
   * @param left left most pos
   * @param right right most pos
   * @param top top most pos
   * @param bottom bottom most pos
   */
  case class SolutionState(left: Int, right: Int, top: Int, bottom: Int)

  /**
   * Stores visited cell information.
   * @param blobCells cells containing a bit of blob
   * @param zeroCells cells not containing a bit of blob
   * @param reads the number of cells read so far
   */
  case class SearchState(blobCells: Set[Cell] = Set.empty, zeroCells: Set[Cell] = Set.empty, reads: Int = 0)

}


