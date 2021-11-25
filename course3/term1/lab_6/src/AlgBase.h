#pragma once

#include "Matrix.h"
#include "Common.h"

class AlgBase
{
public:

  AlgBase();

  virtual ~AlgBase();

  void Calculate();

  bool IsRootProcess() const;

  void SetMatrixA(
      const MatrixT * _MatrixA
    );

  void SetMatrixB(
      const MatrixT * _MatrixB
    );

  void SetReturnMatrix(
      MatrixT * _ReturnMatrix
    );

  double GetElapsedTime() const;

protected:

  virtual void CalculateImpl() = 0;

protected:

  int m_ProcessCount   = INVALID;
  int m_CurrentProcess = INVALID;

  const MatrixT * m_MatrixA = nullptr;
  const MatrixT * m_MatrixB = nullptr;
  MatrixT * m_ReturnMatrix = nullptr;

  double m_ElapsedTime = 0.0;
};