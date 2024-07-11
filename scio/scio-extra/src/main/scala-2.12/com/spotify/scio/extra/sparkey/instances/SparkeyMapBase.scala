/*
 * Copyright 2021 Spotify AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spotify.scio.extra.sparkey.instances

trait SparkeyMapBase[K, V] extends Map[K, V] {
  override def -(key: K): Map[K, V] =
    throw new NotImplementedError("Sparkey-backed map; operation not supported.")

  override def +[V1 >: V](kv: (K, V1)): Map[K, V1] =
    throw new NotImplementedError("Sparkey-backed map; operation not supported.")
}
