import axios from 'axios';
import React, { useState, useEffect } from 'react'
import Swal from 'sweetalert2';

const Comment = ({ postId, comment, fetchComments }) => {
    const [userCommented, setUserCommented] = useState(false);
    const [showEditInput, setShowEditInput] = useState(false);
    const [newComment, setNewComment] = useState({        
        comment: "",

    })

    const handleChange = (e)=>{
        setNewComment({
            addedUser: localStorage.getItem('user'),
            comment: e.target.value
        })
    }

    const checkCommentedUser = async (commentId, addedUser) => {
        const response = await axios.get(`http://localhost:8082/api/${commentId}/${addedUser}`);
        setUserCommented(response.data);
    }

    useEffect(() => {
        checkCommentedUser(comment.id, localStorage.getItem("user"));
    }, []);


    const editComment = () => {
        setShowEditInput(!showEditInput);
        
    }

    const updateComment = () =>{
        Swal.fire({
            title: 'Are you sure want to update this comment?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, update it!'
        }).then(async (result) => {
            if (result.isConfirmed) {
                try {
                    await axios.put(`http://localhost:8082/api/comments/${comment.id}`, newComment);
                    fetchComments();
                } catch (error) {
                    console.log(error);
                }

                Swal.fire(
                    'Updated!',
                    'success'
                )
            }
        })
    }

    const deleteComment = () => {
        Swal.fire({
            title: 'Are you sure want to delete this comment?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then(async (result) => {
            if (result.isConfirmed) {
                try {
                    await axios.delete(`http://localhost:8082/api/deleteComment/${comment.id}`);
                    fetchComments();
                } catch (error) {
                    console.log(error);
                }

                Swal.fire(
                    'Deleted!',
                    'Comment has been deleted.',
                    'success'
                )
            }
        })
    }

    return (
        <div className="row">
            <div className='col-sm-1'>
                <img
                    src={"uploads/" + `${comment.userImage}`}
                    style={{ width: "30px", height: "30px", borderRadius: "20px" }}
                />
            </div>
            <div className='col-sm-3 d-flex justify-content-start align-items-center'>{comment.username}</div>
            <div className='col-sm-8 d-flex justify-content-start align-items-center'>{comment.comment}</div>

            {userCommented && 
            
            <div className='col-sm-8 d-flex justify-content-between align-items-center m-2'>
                
                <button onClick={deleteComment} style={{
                    border: 'none',
                    background: 'none',
                }}>
                    Delete
                </button>

                <button onClick={editComment} style={{
                    border: 'none',
                    background: 'none',
                }}>
                    Edit
                </button>

                {showEditInput && (
                    <div className='d-flex flex-row justify-content-between'>
                        <input type='text' onChange={handleChange} value={newComment.comment}/>
                        <button onClick={updateComment}>Update</button>
                    </div>
                )}
            </div>}
        </div>
    )
}

export default Comment