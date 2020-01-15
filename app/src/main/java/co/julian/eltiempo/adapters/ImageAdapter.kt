/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.julian.eltiempo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.julian.eltiempo.data.entity.NasaEntity
import co.julian.eltiempo.databinding.ListItemImageBinding
import co.julian.eltiempo.view.fragments.MainFragmentDirections


class ImageAdapter(val listener : Listener) : ListAdapter<NasaEntity, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(ListItemImageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false),listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

    class PlantViewHolder(
        private val binding: ListItemImageBinding,val listener : Listener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

            binding.apply {
                clickListener = View.OnClickListener {
                    binding.image?.let { image ->
                        navigateToImage(image, it)
                    }
                }

                addFavoriteClickListener = View.OnClickListener {
                    binding.image?.let { image ->



                        listener.onAddFavorite(image,adapterPosition)
                    }

                }
                deleteClickListener = View.OnClickListener {
                    binding.image?.let { image ->
                        listener.onDelete(image)
                    }

                }



            }
        }

        private fun navigateToImage(
            image: NasaEntity,
            view: View
        ) {

            val direction =
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    image.nasa_id
                )
            view.findNavController().navigate(direction)


        }

        fun bind(item: NasaEntity) {
            binding.apply {
                image = item
                executePendingBindings()
            }
        }
    }

    interface Listener{
        fun onAddFavorite(
            image: NasaEntity,
            adapterPosition: Int
        )
        fun onDelete(image: NasaEntity)
    }

}

private class DiffCallback : DiffUtil.ItemCallback<NasaEntity>() {

    override fun areItemsTheSame(oldItem: NasaEntity, newItem: NasaEntity): Boolean {
        return oldItem.nasa_id == newItem.nasa_id
    }

    override fun areContentsTheSame(oldItem: NasaEntity, newItem: NasaEntity): Boolean {
        return oldItem == newItem
    }
}