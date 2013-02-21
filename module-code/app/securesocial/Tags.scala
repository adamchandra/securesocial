/**
 * Copyright 2012 Jorge Aliss (jaliss at gmail dot com) - twitter: @jaliss
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
 *
 */
package securesocial.core


object Tags {
  type Tagged[T] = {type Tag = T}

  /**
    * Tag a type `T` with `Tag`. The resulting type is a subtype of `T`.
    *
    * The resulting type is used to discriminate between type class instances.
    *
    * @see [[scalaz.Tag]] and [[scalaz.Tags]]
    *
    * Credit to Miles Sabin for the idea.
    */
  type @@[T, Tag] = T with Tagged[Tag]


   object Tag {
     /** `subst` specialized to `Id`.
       *
       * @todo According to Miles, @specialized doesn't help here. Maybe manually specialize.
       */
     @inline def apply[A, T](a: A): A @@ T = a.asInstanceOf[A @@ T]
 
     /** Add a tag `T` to `A`. */
     def subst[A, F[_], T](fa: F[A]): F[A @@ T] = fa.asInstanceOf[F[A @@ T]]
 
     /** Remove the tag `T`, leaving `A`. */
     def unsubst[A, F[_], T](fa: F[A @@ T]): F[A] = fa.asInstanceOf[F[A]]
   }

  sealed trait Email
  sealed trait PasswordHashed
  sealed trait PasswordPlain
  sealed trait Hasher

  // type StringTag[T] = @@[String, T]
  // def StringTag[A] = Tag[String, A]
  // def tagPassword(a: String): String @@ PasswordTag = Tag[String, PasswordTag](a)

  def tagPasswordPlain(a: String): String @@ PasswordPlain = Tag[String, PasswordPlain](a)
  def tagPasswordHashed(a: String): String @@ PasswordHashed = Tag[String, PasswordHashed](a)
  def tagEmail(a: String): String @@ Email = Tag[String, Email](a)
  def tagHasher(a: String): String @@ Hasher = Tag[String, Hasher](a)

  type EmailOf[A] = A @@ Email

}
