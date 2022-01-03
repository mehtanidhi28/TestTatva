package com.example.testtatva.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testtatva.R
import com.example.testtatva.databinding.ItemUserListBinding
import com.example.testtatva.ui.model.UserListResponseModel
import com.example.testtatva.ui.network.State

class UserListAdapter : PagingDataAdapter<UserListResponseModel.UserModel, RecyclerView.ViewHolder>(
    DIFF_CALL
) {

    private var networkState: State? = null

    private fun setUserData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserListAdapter.UserListHolder) {
            val model = getItem(position)
            holder.apply {
                bind(model!!)
            }
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState !== State.LOADING
    }

    override fun getItemViewType(position: Int): Int {
        return 0/*if (hasExtraRow() && position == itemCount - 1) {
            R.layout.loading
        } else
            R.layout.item_user_list*/
    }

    fun setNetworkState(newNetworkState: State) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemInserted(itemCount - 1)
        }
    }

    inner class UserListHolder(val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: UserListResponseModel.UserModel) {
            binding.apply {
                //setVariable(com.example.testtatva.BR.model, model)
                executePendingBindings()
            }
        }
    }

    companion object {
        val DIFF_CALL: DiffUtil.ItemCallback<UserListResponseModel.UserModel> =
            object : DiffUtil.ItemCallback<UserListResponseModel.UserModel>() {
                override fun areItemsTheSame(
                    oldItem: UserListResponseModel.UserModel,
                    newItem: UserListResponseModel.UserModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: UserListResponseModel.UserModel,
                    newItem: UserListResponseModel.UserModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_user_list -> setUserData(holder, position)

            //R.layout.loading -> (holder as NetworkStateItemViewHolder).bindView(networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_user_list -> {
                val binding = ItemUserListBinding.inflate(layoutInflater, parent, false)
                UserListHolder(binding)
            }
            /* R.layout.loading -> {
                 val view = layoutInflater.inflate(R.layout.loading, parent, false)
                 return NetworkStateItemViewHolder(view)
             }*/
            else -> throw IllegalArgumentException("unknown error")
        }
    }
}