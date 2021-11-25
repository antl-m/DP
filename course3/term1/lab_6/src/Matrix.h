#pragma once

#include <vector>
#include <iosfwd>

class MatrixT
{
public:

  MatrixT(
      const std::size_t _H,
      const std::size_t _W
    );

  const std::vector<long long> & operator[](
      const std::size_t _RowIndex
    ) const;

  std::vector<long long> & operator[](
      const std::size_t _RowIndex
    );

  std::size_t GetH() const;

  std::size_t GetW() const;

private:

  std::vector<std::vector<long long>> m_Data;
  const std::size_t             m_H;
  const std::size_t             m_W;
};

MatrixT GetRandomMatrix(
    const std::size_t _H,
    const std::size_t _W
  );

std::ostream & operator<<(
    std::ostream &  _Out,
    const MatrixT & _Matrix
  );