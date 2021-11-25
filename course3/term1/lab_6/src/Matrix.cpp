#include "Matrix.h"
#include "Random.h"

#include <iomanip>

MatrixT::MatrixT(
    const std::size_t _H,
    const std::size_t _W
  ) :
    m_Data(_H, std::vector<long long>(_W, 0)),
    m_H   (_H),
    m_W   (_W)
{
  // Empty
}

const std::vector<long long> & MatrixT::operator[](
    const std::size_t _RowIndex
  ) const
{
  return m_Data[_RowIndex];
}

std::vector<long long> & MatrixT::operator[](
    const std::size_t _RowIndex
  )
{
  return m_Data[_RowIndex];
}

std::size_t MatrixT::GetH() const
{
  return m_H;
}

std::size_t MatrixT::GetW() const
{
  return m_W;
}

MatrixT GetRandomMatrix(
    const std::size_t _H,
    const std::size_t _W
  )
{
  Random  Rand  (-9, 9);
  MatrixT Result(_H, _W);

  for (std::size_t i = 0; i < Result.GetH(); ++i)
  for (std::size_t j = 0; j < Result.GetW(); ++j)
  {
    Result[i][j] = Rand.Get();
  }

  return Result;
}

std::ostream & operator<<(
    std::ostream &  _Out,
    const MatrixT & _Matrix
  )
{
  bool IsFirstRow = true;
  for (size_t h = 0; h < _Matrix.GetH(); ++h)
  {
    if (IsFirstRow)
      IsFirstRow = false;
    else
      _Out << "\n";


    bool IsFirst = true;
    for (size_t w = 0; w < _Matrix.GetW(); ++w)
    {
      if (IsFirst)
        IsFirst = false;
      else
        _Out << " ";

      _Out << std::setw(4) << _Matrix[h][w];
    }
  }
  return _Out;
}
