#include "Common.h"
#include "Matrix.h"
#include "SequencedAlg.h"

#include <iostream>

int main(int argc, char ** argv )
{
  static const MatrixT MatrixA = GetRandomMatrix(DEFAULT_H, DEFAULT_W);
  static const MatrixT MatrixB = GetRandomMatrix(DEFAULT_W, DEFAULT_H);
  static MatrixT MatrixC(DEFAULT_H, DEFAULT_H);

  MPIGuard Guard(argc, argv);

  SequencedAlg Alg;
  Alg.SetMatrixA(&MatrixA);
  Alg.SetMatrixB(&MatrixB);
  Alg.SetReturnMatrix(&MatrixC);

  Alg.Calculate();

  if (Alg.IsRootProcess())
  {
    std::cout << "Matrix A:\n" << MatrixA << "\nMatrix B:\n" << MatrixB << "\nMatrix C:\n" << MatrixC << "\n";
    std::cout << "Elapsed time: " << Alg.GetElapsedTime() << std::endl;
  }

  return 0;
}