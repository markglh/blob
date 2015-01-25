package com.markglh.blob

import com.example.State._
import com.markglh.blob.grid.Grid.Cell
import com.markglh.blob.grid.{Grid, GridLoader}
import com.markglh.blob.pickers.CellPicker

import scala.annotation.tailrec

/**
 * Finds the boundaries of a blob.
 *
 * @param cellPicker The picker implementation for finding the blob.
 * @param gridLoader The loader for the grid, allows the grid to be either hard coded or from a file.
 */
class BlobFinder(implicit val cellPicker: CellPicker, gridLoader: GridLoader) {

  val grid: Grid = gridLoader.loadGrid()

  /**
   * Recursively find a solution by invoking findNext on the unsearched cells in the grid until the no more viable cells exist.
   */
  @tailrec final def findBlob(globalState: GlobalState = GlobalState()): GlobalState = {
    cellPicker.findNext(grid, globalState) match {
      case None => globalState
      case Some(cell) => findBlob(saveState(cell, globalState))
    }
  }

  /**
   * Creates a new GlobalState by comparing the current cell to all directions to ascertain whether the blob is larger.
   */
  private def saveState(currentCell: Cell, globalState: GlobalState): GlobalState = {
    def calculateNewBlobBoundaries(left: Int, right: Int, top: Int, bottom: Int): SolutionState = {
      SolutionState(
        left.min(currentCell._1),
        right.max(currentCell._1),
        top.min(currentCell._2),
        bottom.max(currentCell._2))
    }

    //does the cell contain any blob
    if (grid.gridCells(currentCell)) {
      GlobalState(
        SearchState(globalState.searchState.blobCells + currentCell, globalState.searchState.zeroCells, 1 + globalState.searchState.reads),
        globalState.solutionState match {
          case None =>
            SolutionState(currentCell._1, currentCell._1, currentCell._2, currentCell._2)
          case Some(SolutionState(left, right, top, bottom)) =>
            calculateNewBlobBoundaries(left, right, top, bottom)
        }
      )
    } else {
      GlobalState(
        SearchState(globalState.searchState.blobCells, globalState.searchState.zeroCells + currentCell, 1 + globalState.searchState.reads),
        globalState.solutionState)
    }
  }
}
