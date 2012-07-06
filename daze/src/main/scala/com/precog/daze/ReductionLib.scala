/*
 *  ____    ____    _____    ____    ___     ____ 
 * |  _ \  |  _ \  | ____|  / ___|  / _/    / ___|        Precog (R)
 * | |_) | | |_) | |  _|   | |     | |  /| | |  _         Advanced Analytics Engine for NoSQL Data
 * |  __/  |  _ <  | |___  | |___  |/ _| | | |_| |        Copyright (C) 2010 - 2013 SlamData, Inc.
 * |_|     |_| \_\ |_____|  \____|   /__/   \____|        All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the 
 * GNU Affero General Public License as published by the Free Software Foundation, either version 
 * 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 * the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this 
 * program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.precog
package daze

import bytecode.Library
import bytecode.Arity

import yggdrasil._
import yggdrasil.table._

import scalaz.Monoid
import scalaz.std.anyVal._

trait ReductionLib extends GenOpcode with ImplLibrary with BigDecimalOperations with Evaluator {  
  val ReductionNamespace = Vector()

  override def _libReduction = super._libReduction ++ Set(Count, Max, Min, Sum, Mean, GeometricMean, SumSq, Variance, StdDev)
  override def _libMorphism = super._libMorphism ++ Set(Median, Mode)

  // TODO swap to Reduction
  object Count extends Reduction(ReductionNamespace, "count") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      Some(SDecimal(BigDecimal(enum.count))) 
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }

  object Max extends Reduction(ReductionNamespace, "max") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val max: Option[BigDecimal] = enum.reduce(Option.empty[BigDecimal]) {
        case (None, SDecimal(v)) => Some(v)
        case (Some(v1), SDecimal(v2)) if v1 >= v2 => Some(v1)
        case (Some(v1), SDecimal(v2)) if v1 < v2 => Some(v2)
        case (acc, _) => acc
      }

      if (max.isDefined) max map { v => SDecimal(v) }
      else None
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }

  object Min extends Reduction(ReductionNamespace, "min") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val min = enum.reduce(Option.empty[BigDecimal]) {
        case (None, SDecimal(v)) => Some(v)
        case (Some(v1), SDecimal(v2)) if v1 <= v2 => Some(v1)
        case (Some(v1), SDecimal(v2)) if v1 > v2 => Some(v2)
        case (acc, _) => acc
      }
      
      if (min.isDefined) min map { v => SDecimal(v) }
      else None
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }
  
  object Sum extends Reduction(ReductionNamespace, "sum") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val sum = enum.reduce(Option.empty[BigDecimal]) {
        case (None, SDecimal(v)) => Some(v)
        case (Some(sum), SDecimal(v)) => Some(sum + v)
        case (acc, _) => acc
      }

      if (sum.isDefined) sum map { v => SDecimal(v) }
      else None
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }
  
  object Mean extends Reduction(ReductionNamespace, "mean") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val (count, total) = enum.reduce((BigDecimal(0), BigDecimal(0))) {
        case ((count, total), SDecimal(v)) => (count + 1, total + v)
        case (total, _) => total
      }
      
      if (count == BigDecimal(0)) None
      else Some(SDecimal(total / count))
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }
  
  object GeometricMean extends Reduction(ReductionNamespace, "geometricMean") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val (count, total) = enum.reduce((BigDecimal(0), BigDecimal(1))) {
        case ((count, acc), SDecimal(v)) => (count + 1, acc * v)
        case (acc, _) => acc
      }
      
      if (count == BigDecimal(0)) None
      else Some(SDecimal(Math.pow(total.toDouble, 1 / count.toDouble)))
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }
  
  object SumSq extends Reduction(ReductionNamespace, "sumSq") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val sumsq = enum.reduce(Option.empty[BigDecimal]) {
        case (None, SDecimal(v)) => Some(v * v)
        case (Some(sumsq), SDecimal(v)) => Some(sumsq + (v * v))
        case (acc, _) => acc
      }

      if (sumsq.isDefined) sumsq map { v => SDecimal(v) }
      else None
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }
  
  object Variance extends Reduction(ReductionNamespace, "variance") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val (count, sum, sumsq) = enum.reduce((BigDecimal(0), BigDecimal(0), BigDecimal(0))) {
        case ((count, sum, sumsq), SDecimal(v)) => (count + 1, sum + v, sumsq + (v * v))
        case (acc, _) => acc
      }

      if (count == BigDecimal(0)) None
      else Some(SDecimal((sumsq - (sum * (sum / count))) / count))
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }
  
  object StdDev extends Reduction(ReductionNamespace, "stdDev") {
    type Result = Int
    
    def monoid = implicitly[Monoid[Int]]
    
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val (count, sum, sumsq) = enum.reduce((BigDecimal(0), BigDecimal(0), BigDecimal(0))) {
        case ((count, sum, sumsq), SDecimal(v)) => (count + 1, sum + v, sumsq + (v * v))
        case (acc, _) => acc
      }
      
      if (count == BigDecimal(0)) None
      else Some(SDecimal(sqrt(count * sumsq - sum * sum) / count))
    } */
    
    def reducer: CReducer[Int] = new CReducer[Int] {
      def reduce(col: Column, range: Range) = 0
    }
  }
  
  object Median extends Morphism(ReductionNamespace, "median", Arity.One) {
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val enum2 = enum.sortByValue(graph.memoId, ctx.memoizationContext)

      val count = enum.reduce(BigDecimal(0)) {
        case (count, SDecimal(v)) => count + 1
        case (acc, _) => acc
      }
      
      if (count == BigDecimal(0)) None
      else {
        val (c, median) = if (count.toInt % 2 == 0) {
          val index = (count.toInt / 2, (count.toInt / 2) + 1)
        
          enum2.reduce((BigDecimal(0), Option.empty[BigDecimal])) {
            case ((count, _), SDecimal(v)) if (count + 1 < index._2) => (count + 1, Some(v))
            case ((count, prev), SDecimal(v)) if (count + 1 == index._2) => {
              (count + 1, 
                if (prev.isDefined) prev map { x => (x + v) / 2 } 
                else None)  
            }
            case (acc, _) => acc
          } 
        } else {
          val index = (count.toInt / 2) + 1
        
          enum2.reduce(BigDecimal(0), Option.empty[BigDecimal]) {
            case ((count, _), SDecimal(_)) if (count + 1 < index) => (count + 1, None)
            case ((count, _), SDecimal(v)) if (count + 1 == index) => (count + 1, Some(v))
            case (acc, _) => acc
          }
        }
        if (median.isDefined) median map { v => SDecimal(v) }
        else None
      }
    } */
    
    lazy val alignment = None

    def apply(table: Table) = table
  }
  
  object Mode extends Morphism(ReductionNamespace, "mode", Arity.One) {
    /* def reduced(enum: Dataset[SValue], graph: DepGraph, ctx: Context): Option[SValue] = {
      val enum2 = enum.sortByValue(graph.memoId, ctx.memoizationContext)

      val (_, _, modes, _) = enum2.reduce(Option.empty[SValue], BigDecimal(0), List.empty[SValue], BigDecimal(0)) {
        case ((None, count, modes, maxCount), sv) => ((Some(sv), count + 1, List(sv), maxCount + 1))
        case ((Some(currentRun), count, modes, maxCount), sv) => {
          if (currentRun == sv) {
            if (count >= maxCount)
              (Some(sv), count + 1, List(sv), maxCount + 1)
            else if (count + 1 == maxCount)
              (Some(sv), count + 1, modes :+ sv, maxCount)
            else
              (Some(sv), count + 1, modes, maxCount)
          } else {
            if (maxCount == 1)
              (Some(sv), 1, modes :+ sv, maxCount)
            else
              (Some(sv), 1, modes, maxCount)
          }
        }

        case(acc, _) => acc
      }
      
      Some(SArray(Vector(modes: _*))) 
    } */
    
    lazy val alignment = None

    def apply(table: Table) = table
  }
}
