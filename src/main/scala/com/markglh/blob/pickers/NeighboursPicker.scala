package com.markglh.blob.pickers

import com.example.State.GlobalState
import com.markglh.blob.grid.Grid
import com.markglh.blob.grid.Grid.Cell

import scala.util.Random

/**
 * Once a cell containing the blob is found then we check all cells around the cell for more blob.
 */
class NeighboursPicker extends CellPicker {

  private def findViableNeighbourCell(grid: Grid, globalState: GlobalState): Option[Cell] = {
    val unvisitedBlobNeighbours = globalState.searchState.blobCells.
      flatMap { cell => findCellNeighboursWithinGrid(cell, grid)}. //find neighbours
      filter(grid.getUnvisitedCells(globalState.searchState).contains) //filter visited cells

    //find any cell from our set that is on the edge or outside our current known blob
    unvisitedBlobNeighbours.find(cell => cell._1 <= globalState.solutionState.get.left ||
      cell._1 >= globalState.solutionState.get.right ||
      cell._2 <= globalState.solutionState.get.top ||
      cell._2 >= globalState.solutionState.get.bottom)
  }

  private def findCellNeighboursWithinGrid(cell: Cell, grid: Grid): Set[Cell] = {
    Set((cell._1 - 1, cell._2), (cell._1 + 1, cell._2), (cell._1, cell._2 - 1), (cell._1, cell._2 + 1))
      .filter(neighbourCell =>
      neighbourCell._1 >= 0 && neighbourCell._1 < grid.width &&
        neighbourCell._2 >= 0 && neighbourCell._2 < grid.height)
  }

  /**
   * Find the next Cell by picking unvisited Cells surrounding the visited ones.
   */
  def findNext(grid: Grid, globalState: GlobalState): Option[Cell] = globalState.solutionState match {
    case None => Random.shuffle(grid.getUnvisitedCells(globalState.searchState)).headOption
    case Some(solutionState) => findViableNeighbourCell(grid, globalState)
  }
}
