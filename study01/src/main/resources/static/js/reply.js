async function get1(bno){
    const result = await axios.get(`/replies/list/${bno}`)
    return result
}
async function getList({bno, page, size, goLast}){
    const result = await axios.get(`/replies/list/${bno}`, {params : {page, size}})

    if(goLast) { //마지막 페이지 출력
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({bno:bno, page:lastPage, size:size})
    }
    return result.data;
}
async function addReply(replyObj) {
    const response = await axios.post(`/replies/`, replyObj) // repliesObj : 내용
    return response.data;
}
async function getReply(rno) { //데이터 조회
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}
async function modifyReply(replyObj){ //put 사용해 데이터 수정
    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj) // 해당 데이터 전송
    return response.data
}
async function removeReply(rno){
    const response = await axios.delete(`/replies/${rno}`) // delete를 사용해 데이터 삭제
    return response.data
}