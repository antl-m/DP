#include "SequencedAlg.h"

void SequencedAlg::CalculateImpl()
{
  if (IsRootProcess())
  {
    for (size_t h = 0; h < m_ReturnMatrix->GetH(); ++h)
    for (size_t w = 0; w < m_ReturnMatrix->GetW(); ++w)
    {
      (*m_ReturnMatrix)[h][w] = 0;
      for (size_t k = 0; k < m_MatrixA->GetW(); ++k)
        (*m_ReturnMatrix)[h][w] += (*m_MatrixA)[h][k] * (*m_MatrixB)[k][w];
    }
  }
}
