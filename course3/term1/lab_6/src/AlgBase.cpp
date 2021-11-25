#include "AlgBase.h"
#include <mpi.h>


AlgBase::AlgBase()
{
  MPI_Comm_size(MPI_COMM_WORLD, &m_ProcessCount);
  MPI_Comm_rank(MPI_COMM_WORLD, &m_CurrentProcess);
}

AlgBase::~AlgBase() = default;

void AlgBase::Calculate()
{
  double StartTime = MPI_Wtime();
  CalculateImpl();
  double EndTime = MPI_Wtime();
  m_ElapsedTime = EndTime - StartTime;
}

bool AlgBase::IsRootProcess() const
{
  return m_CurrentProcess == 0;
}

void AlgBase::SetMatrixA(
    const MatrixT * _MatrixA
  )
{
  m_MatrixA = _MatrixA;
}

void AlgBase::SetMatrixB(
    const MatrixT * _MatrixB
  )
{
  m_MatrixB = _MatrixB;
}

void AlgBase::SetReturnMatrix(
    MatrixT * _ReturnMatrix
  )
{
  m_ReturnMatrix = _ReturnMatrix;
}

double AlgBase::GetElapsedTime() const
{
  return m_ElapsedTime;
}
