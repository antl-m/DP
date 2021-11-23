#pragma once

#include <array>
#include <random>
#include <type_traits>

inline constexpr int INVALID = -1;

using NumT = int;
using Distribution = std::uniform_int_distribution<NumT>;


inline constexpr int M = 100;
inline constexpr int N = 100;

using MatrixAT = std::array<std::array<NumT, N>, M>;
using MatrixBT = std::array<std::array<NumT, M>, N>;
using MatrixCT = std::array<std::array<NumT, M>, M>;

class Random
{
public:

  Random(
      const NumT _Min,
      const NumT _Max
    );

  NumT Get();

private:

  std::default_random_engine m_Engine;
  Distribution               m_Distribution;
};

MatrixAT GetRandomMatrixA();
MatrixBT GetRandomMatrixB();
