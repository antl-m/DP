#include "Random.h"

Random::Random(
    const long long _Min,
    const long long _Max
  ) :
    m_RandomDevice(),
    m_Engine      (m_RandomDevice()),
    m_Distribution(_Min, _Max)
{
  // Empty
}

long long Random::Get()
{
  return m_Distribution(m_Engine);
}
