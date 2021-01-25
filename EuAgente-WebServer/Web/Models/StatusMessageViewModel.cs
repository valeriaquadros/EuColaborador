namespace Web.Models
{
    public class StatusMessageViewModel
    {
        public string StatusMessage { get; set; }

        public StatusMessageViewModel(string statusMessage)
        {
            StatusMessage = statusMessage;
        }
    }
}
